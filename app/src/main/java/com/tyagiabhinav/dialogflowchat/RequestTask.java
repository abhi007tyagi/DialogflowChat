package com.tyagiabhinav.dialogflowchat;

import android.app.Activity;
import android.os.AsyncTask;

import ai.api.AIServiceContext;
import ai.api.AIServiceException;
import ai.api.android.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

public class RequestTask extends AsyncTask<AIRequest, Void, AIResponse> {

    Activity activity;
    AIDataService aiDataService;
    AIServiceContext customContext;

    public RequestTask(Activity activity, AIDataService aiDataService, AIServiceContext customContext){
        this.activity = activity;
        this.aiDataService = aiDataService;
        this.customContext = customContext;
    }


    @Override
    protected AIResponse doInBackground(AIRequest... aiRequests) {
        final AIRequest request = aiRequests[0];
        try {
            return aiDataService.request(request, customContext);
        } catch (AIServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(AIResponse aiResponse) {
        ((MainActivity)activity).callback(aiResponse);
    }
}
