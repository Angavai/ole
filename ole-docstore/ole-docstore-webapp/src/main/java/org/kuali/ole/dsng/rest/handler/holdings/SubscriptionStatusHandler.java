package org.kuali.ole.dsng.rest.handler.holdings;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.kuali.ole.constants.OleNGConstants;
import org.kuali.ole.docstore.engine.service.storage.rdbms.pojo.HoldingsRecord;
import org.kuali.ole.dsng.rest.Exchange;
import org.kuali.ole.dsng.rest.handler.holdings.HoldingsHandler;

import java.util.Iterator;
import java.util.List;

/**
 * Created by SheikS on 12/31/2015.
 */
public class SubscriptionStatusHandler extends HoldingsHandler {

    private final String TYPE = "Subscription Status";

    @Override
    public Boolean isInterested(String operation) {
        return operation.equals(TYPE);
    }

    @Override
    public void process(JSONObject requestJsonObject, Exchange exchange) {
        HoldingsRecord holdingRecord = (HoldingsRecord) exchange.get(OleNGConstants.HOLDINGS_RECORD);
        String subscriptionStatus = getStringValueFromJsonObject(requestJsonObject, TYPE);
        List<String> parsedValues = parseCommaSeperatedValues(subscriptionStatus);
        for (Iterator<String> iterator = parsedValues.iterator(); iterator.hasNext(); ) {
            String subscriptionStatusValue = iterator.next();
            if (StringUtils.equals(holdingRecord.getSubscriptionStatus(), subscriptionStatusValue)) {
                exchange.add(OleNGConstants.MATCHED_HOLDINGS, Boolean.TRUE);
                exchange.add(OleNGConstants.MATCHED_VALUE, subscriptionStatusValue);
            }
        }
    }

    @Override
    public void processDataMappings(JSONObject requestJsonObject, Exchange exchange) {
        JSONArray jsonArrayeFromJsonObject = getJSONArrayeFromJsonObject(requestJsonObject, TYPE);
        List<String> listFromJSONArray = getListFromJSONArray(jsonArrayeFromJsonObject.toString());
        if(CollectionUtils.isNotEmpty(listFromJSONArray)) {
            String subscriptionStatus = listFromJSONArray.get(0);
            HoldingsRecord holdingRecord = (HoldingsRecord) exchange.get(OleNGConstants.HOLDINGS_RECORD);
            holdingRecord.setSubscriptionStatus(subscriptionStatus);
            exchange.add(OleNGConstants.HOLDINGS_RECORD, holdingRecord);
        }
    }
}
