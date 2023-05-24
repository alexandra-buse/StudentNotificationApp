package com.example.studentnotificationapp.ui.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentnotificationapp.R;
import com.example.studentnotificationapp.Register;
import com.example.studentnotificationapp.databinding.FragmentGalleryBinding;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.messaging.CommonNotificationBuilder;
import com.android.volley.Response;
import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GalleryFragment extends Fragment {

    private static final String[] Topics = new String[]{
            "Licenta", "Master", "Automatica", "Calculatoare", "1", "2",
            "3", "4","AA","AB","AC","CA","CB","CC","CD"
    };

    private MultiAutoCompleteTextView choseTopic;
    private Button send;
    private FragmentGalleryBinding binding;


    String userFcmToken;
    String title;
    String body;
    Context mContext;
    Activity mActivity;
    private RequestQueue requestQueue;
    private final String postUrl = "https://fcm.googleapis.com/fcm/send";
    private final String fcmServerKey ="AAAAgLt8zPI:APA91bGLqAlhuDd7JdwgTEHsMghTJix-cwKVbVEMgMFRF45bqvb-V5ophFJ6Q3E-4hyLQlvJGsJ9J89qr5ILfHv3xRScXA_CqeVgu2ScwyypJt_ik80bNaWH93namlZ_1sszeU9ZweVa";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        choseTopic = binding.choseTopics;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, Topics);
        choseTopic.setAdapter(adapter);
        choseTopic.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        final View button = binding.sendNotif;
        mContext = getContext();
        mActivity = getActivity();
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Hei", Toast.LENGTH_SHORT).show();
                        composeAndSendNotification();
                    }
                }
        );

        //final TextView textView = binding.textGallery;
        //galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void composeAndSendNotification() {

        requestQueue = Volley.newRequestQueue(mActivity);
        JSONObject mainObj = new JSONObject();
        title = binding.notifTitle.getText().toString();
        body = binding.notifDesc.getText().toString();
        String topic = choseTopic.getText().toString().trim();
        String processedTopic = topic.replace(",","");
        Log.d("STATE", processedTopic);
        try {
            //mainObj.put("to", userFcmToken);
            mainObj.put("to", "/topics/" + processedTopic);
            JSONObject notiObject = new JSONObject();
            notiObject.put("title", title);
            notiObject.put("body", body);
           // notiObject.put("icon", "icon"); // enter icon that exists in drawable only



            mainObj.put("notification", notiObject);
            Log.d("STATE", notiObject.toString());
            Log.d("STATE", mainObj.toString());

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, postUrl, mainObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getActivity(), "Correctos", Toast.LENGTH_SHORT).show();
                    // code run is got response
                    Log.d("STATE", response.toString());

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // code run is got error
                    Toast.makeText(getActivity(), "Erroros", Toast.LENGTH_SHORT).show();

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {


                    Map<String, String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=" + fcmServerKey);
                    return header;


                }
            };
            requestQueue.add(request);


        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}