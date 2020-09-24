package com.reactlibrary;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RecyclerViewManager extends ViewGroupManager<RecyclerView> {

    public static final String REACT_CLASS = "RecyclerView";
    private ArrayList<Movie> movieList = new ArrayList<Movie>();
    private MoviesAdapter adapter;
    private ThemedReactContext reactContext;
    private RecyclerView recyclerView;
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public RecyclerView createViewInstance(ThemedReactContext c) {
        reactContext = c;
        recyclerView = new RecyclerView(c);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager layout = new LinearLayoutManager(c, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layout);

        adapter = new MoviesAdapter(movieList, c);
        recyclerView.setAdapter(adapter);
        setListeners();

        return recyclerView;

    }
    public Map getExportedCustomBubblingEventTypeConstants(){
        return MapBuilder.builder()
                .put("onPress", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onClick"))).build();
    }
    public void onReceiveNativeEvent(int position, ThemedReactContext context){
        WritableMap event = Arguments.createMap();
        event.putString("id", movieList.get(position).getMovieId());
        context.getJSModule(RCTEventEmitter.class).receiveEvent(
                recyclerView.getId(),
                "onPress",
                event);
    }
    private void setListeners(){
        adapter.setOnItemClickListener(new MoviesAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                onReceiveNativeEvent(position, reactContext);
            }
        });
    }
    @ReactProp(name = "data")
    public void setData(RecyclerView view, ReadableArray data){
        for (int i = 0; i < data.size(); i++){
            String poster_url = data.getMap(i).getString("poster_url");
            String title = data.getMap(i).getString("title");
            String _id = data.getMap(i).getString("_id");

            Movie movieData = new Movie(title,poster_url,_id);
            movieList.add(i, movieData);
        }
        //movieList.subList(data.size(), movieList.size()).clear();

    }
}
