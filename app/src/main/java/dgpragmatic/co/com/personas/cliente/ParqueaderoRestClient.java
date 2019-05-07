package dgpragmatic.co.com.personas.cliente;


import android.content.Context;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class ParqueaderoRestClient {
    public String ipServidor;
    private String BASE_URL = "http:/".concat(ipServidor).concat(":8080/");
    private AsyncHttpClient client = new AsyncHttpClient();

    public ParqueaderoRestClient(String ip) {
        this.ipServidor=ip;
    }


    public void get(Context context, String url, Header[] headers, RequestParams params,
                    AsyncHttpResponseHandler responseHandler) {
        client.get(context, getAbsoluteUrl(url), headers, params, responseHandler);

    }

    public void set(Context context, String url, StringEntity entity, TextHttpResponseHandler responseHandler) {
        client.post(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
