package com.tedx.capetown.app.core.service.impl;

import android.content.Intent;
import android.util.Log;
import com.tedx.capetown.app.core.converter.impl.SponsorCollectionConverter;
import com.tedx.capetown.app.core.models.SponsorCollectionModel;
import com.tedx.capetown.app.core.service.SDKConnectorRequest;
import com.tedx.capetown.app.core.service.request.SponsorCollectionServiceRequest;
import com.tedx.capetown.app.core.service.request.SponsorModelServiceRequest;
import com.tedx.capetown.lib.sdk.connector.SponsorConnector;
import com.tedx.capetown.lib.sdk.connector.request.impl.SponsorRequest;
import com.tedx.capetown.lib.sdk.dto.SDKResponse;
import com.tedx.capetown.lib.sdk.dto.SponsorCollectionDTO;
import com.tedx.capetown.lib.sdk.exception.SDKException;

import java.io.IOException;
import java.text.ParseException;

import de.greenrobot.event.EventBus;

public class SponsorService extends AbstractSDKIntentService {

    public SponsorService() {
        super(ServiceIdentifier.Sponsor.name());
    }

    private SponsorCollectionModel fetchSponsorList() throws IllegalArgumentException, IllegalAccessException, IOException, SDKException, ParseException {
        return executeSdkConnectorRequest(
            new SDKConnectorRequest<SponsorCollectionDTO>() {
                @Override
                public SDKResponse<SponsorCollectionDTO> makeRequest() throws IOException,
                        SDKException,
                        ParseException {
                    SponsorConnector SponsorConnector = getSDKClient().getSponsorConnector();
                    SponsorRequest request = SponsorConnector.getSponsorRequestBuilder("tedx_server/response/sponsors.php").build();
                    SDKResponse<SponsorCollectionDTO> response = SponsorConnector.getSponsorList(request);
                    return response;
                }
            }
            , new SponsorCollectionConverter(SponsorCollectionDTO.class,SponsorCollectionModel.class)
        );
    }
    @Override
    protected void onHandleIntentAfterInitialization(Intent intent) {
        String action = intent.getAction();
        if (action == null)
            return;
        if (action.equals(SponsorCollectionServiceRequest.class.getName())) {
            try {
                SponsorCollectionModel sponsorCollectionModel = fetchSponsorList();
                EventBus.getDefault().postSticky(sponsorCollectionModel);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SDKException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if (action.equals(SponsorModelServiceRequest.class.getName()))
        {
            Log.wtf("TEST","SponsorModelServiceRequest: "+action);
        }
        Log.wtf("TEST","action"+action);
    }

}
