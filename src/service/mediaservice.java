package service;

import fragment.fragmentaudio;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class mediaservice extends Service{
fragmentaudio fragmentaudio = new fragmentaudio();
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
	
		
		return super.onStartCommand(intent, flags, startId);
	}

}
