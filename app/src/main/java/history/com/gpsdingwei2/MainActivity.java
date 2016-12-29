package history.com.gpsdingwei2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;
public class MainActivity extends AppCompatActivity {

    private Button btn;
    protected LocationManager  locationManager;
    protected Location mlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn =(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

                //首先判断网络是否可用
                if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                }else{
                    //将手机位置服务中--基于网络的位置服务关闭后，则获取不到数据
                    showgps("NETWORK_PROVIDER不可用，无法获取GPS信息！");
                }
                List<String> allprovides=locationManager.getAllProviders();
                for(String allprovide:allprovides){
                    Log.i("Test", allprovide);
                }
            }
        });

    }
    protected final LocationListener locationListener=new LocationListener() {

        // Provider的在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }
        //  Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }
        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }
        //当坐标改变时触发此函数
        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            mlocation=location;
            //解除监听
            locationManager.removeUpdates(locationListener);
            String  gpsinfo="GPS: Latitude="+mlocation.getLatitude()+"   Longitude="+mlocation.getLongitude();
            showgps(gpsinfo);
        }
    };
    public void showgps(String info){
        btn.setText(info);
    }

}
