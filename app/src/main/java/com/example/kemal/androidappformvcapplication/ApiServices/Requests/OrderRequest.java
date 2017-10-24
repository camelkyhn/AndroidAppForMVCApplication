package com.example.kemal.androidappformvcapplication.ApiServices.Requests;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.kemal.androidappformvcapplication.Entities.Order;
import com.example.kemal.androidappformvcapplication.Entities.OrderDetail;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRequest extends StringRequest
{
    private final static String apiUrl = "http://192.168.1.120:10617/api/OrdersApi/PostOrder";
    private Map<String, String> headers;
    private Order Order;

    public OrderRequest(Order order, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Request.Method.POST, apiUrl, listener, errorListener);
        this.Order = order;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError
    {
        headers = new HashMap<>();
        headers.put("content-type", "application/json");
        return headers;
    }

    @Override
    public String getBodyContentType()
    {
        return "application/json";
    }

    @Override
    public byte[] getBody() throws AuthFailureError
    {
        String jsonBodyString = "{" +
                "\"userName\": " + "\"" + Order.getUserName() + "\"," +
                "\"firstName\": " + "\"" + Order.getFirstName() + "\"," +
                "\"lastName\": " + "\"" + Order.getLastName() + "\"," +
                "\"address\": " + "\"" + Order.getAddress() + "\"," +
                "\"email\": " + "\"" + Order.getEmail() + "\"," +
                "\"phoneNumber\": " + "\"" + Order.getPhoneNumber() + "\"," +
                "\"companyName\": " + "\"" + Order.getCompanyName() + "\"," +
                "\"shippingName\": " + "\"" + Order.getShippingName() + "\"," +
                "\"totalPrice\": " + "\"" + Order.getTotalPrice() + "\"," +
                "\"orderDetails\": " + "[" + getOrderDetails();
        return jsonBodyString.getBytes();
    }

    private String getOrderDetails()
    {
        String jsonBodyString = "";
        List<OrderDetail> orderDetails = this.Order.getOrderDetails();
        for(int i=0; i<orderDetails.size();i++)
        {
            jsonBodyString = jsonBodyString + "{\"isActive\": " + "\"true\",";
            jsonBodyString = jsonBodyString + "\"productID\": " + "\"" + orderDetails.get(i).getProductID() + "\",";
            jsonBodyString = jsonBodyString + "\"quantity\": " + "\"" + orderDetails.get(i).getQuantity() + "\",";
            jsonBodyString = jsonBodyString + "\"unitPrice\": " + "\"" + orderDetails.get(i).getPrice() + "\"}";
            if (i==orderDetails.size()-1)
            {
                jsonBodyString = jsonBodyString + "]}";
                break;
            }
            else
            {
                jsonBodyString = jsonBodyString + ",";
            }
        }
        return jsonBodyString;
    }

/*    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response)
    {
        int statusCode = response.statusCode;
        if (statusCode == 201)
        {
            //Created.
            String responseBody = response.data.toString();
        }
        else
        {
            //Failed to create.
            Toast.makeText(Context, response.toString(), Toast.LENGTH_SHORT).show();
        }
        return super.parseNetworkResponse(response);
    }*/

    /*    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        params = new HashMap<>();
        params.put("userName", Order.getUserName());
        params.put("firstName", Order.getFirstName());
        params.put("lastName", Order.getLastName());
        params.put("adress", Order.getAddress());
        params.put("email", Order.getEmail());
        params.put("phoneNumber", Order.getPhoneNumber());
        params.put("companyName", Order.getCompanyName());
        params.put("shippingName", Order.getShippingName());
        params.put("totalPrice", Order.getTotalPrice().toString());

        List<OrderDetail> orderDetails = Order.getOrderDetails();
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i<orderDetails.size(); i++)
        {
            jsonObject = new JSONObject();
            try
            {
                jsonObject.put("OrderID", orderDetails.get(i).getOrderID());
                jsonObject.put("ProductID", orderDetails.get(i).getProductID());
                jsonObject.put("Quantity", orderDetails.get(i).getQuantity());
                jsonObject.put("Price", orderDetails.get(i).getPrice());

                jsonArray.put(jsonObject);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        params.put("orderDetails",jsonArray.toString());
        return params;
    }*/
}
