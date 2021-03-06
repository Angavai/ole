package org.kuali.ole.dsng.rest.handler.holdings;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.kuali.ole.Exchange;
import org.kuali.ole.constants.OleNGConstants;
import org.kuali.ole.docstore.engine.service.storage.rdbms.pojo.HoldingsRecord;
import org.kuali.ole.dsng.dao.BibValidationDao;
import org.kuali.ole.dsng.model.HoldingsRecordAndDataMapping;
import org.kuali.ole.dsng.rest.handler.Handler;
import org.kuali.ole.dsng.util.HoldingsUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by SheikS on 2/25/2016.
 */
public class DiscardHoldingsHandler extends Handler {
    @Override
    public Boolean isInterested(String operation) {
        List<String> operationsList = getListFromJSONArray(operation);
        for (Iterator iterator = operationsList.iterator(); iterator.hasNext(); ) {
            String op = (String) iterator.next();
            if(op.equals("123")){
                return true;
            }
        }
        return false;
    }

    @Override
    public void process(JSONObject requestJsonObject, Exchange exchange) {

        List<HoldingsRecordAndDataMapping> holdingsRecordAndDataMappings = (List<HoldingsRecordAndDataMapping>) exchange.get(OleNGConstants.HOLDINGS_FOR_UPDATE);
        List<HoldingsRecord> holdingsRecords = new ArrayList<HoldingsRecord>();
        if (CollectionUtils.isNotEmpty(holdingsRecordAndDataMappings)) {
            try {
                String updatedBy = requestJsonObject.getString(OleNGConstants.UPDATED_BY);
                String updatedDateString = (String) requestJsonObject.get(OleNGConstants.UPDATED_DATE);
                Timestamp updatedDate = getDateTimeStamp(updatedDateString);

                for (Iterator<HoldingsRecordAndDataMapping> iterator = holdingsRecordAndDataMappings.iterator(); iterator.hasNext(); ) {
                    HoldingsRecordAndDataMapping holdingsRecordAndDataMapping = iterator.next();
                    HoldingsRecord holdingsRecord = holdingsRecordAndDataMapping.getHoldingsRecord();
                    holdingsRecord.setUpdatedDate(updatedDate);
                    holdingsRecord.setUpdatedBy(updatedBy);
                    exchange.add(OleNGConstants.HOLDINGS_RECORD,holdingsRecord);
                    JSONObject dataMappingByValue = holdingsRecordAndDataMapping.getDataMapping();
                    if(null != dataMappingByValue) {
                        BibValidationDao bibValidationDao = (BibValidationDao) org.kuali.ole.dsng.service.SpringContext.getBean("bibValidationDao");
                        if (bibValidationDao.isHoldingAttachedToPo(holdingsRecord.getHoldingsId())) {
                            Exception e = new Exception(OleNGConstants.ERR_HOLDINGS_HAS_REQ_OR_PO);
                            addFailureReportToExchange(requestJsonObject, exchange,"holdings",e,null);
                        }else{
                            HoldingsUtil.getInstance().processIfDeleteAllExistOpsFound(holdingsRecord, requestJsonObject);
                        }

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            exchange.remove(OleNGConstants.HOLDINGS_RECORD);
            getOleDsNGMemorizeService().getHoldingDAO().saveAll(holdingsRecords);
        }
    }
}
