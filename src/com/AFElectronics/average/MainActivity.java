package com.AFElectronics.average;


import android.support.v7.app.ActionBarActivity;
import android.text.*;
import android.text.method.DigitsKeyListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import com.google.android.gms.ads.*;

public class MainActivity extends ActionBarActivity {
	EditText  input;
	TextView result;
	Toast error1;
	Toast error2;
	Toast about;
	
	private static final String AD_UNIT_ID = "ca-app-pub-5213700102366429/3333445991";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // inicializar a actividade
		
		// Look up the AdView as a resource and load a request.
	    AdView adView = (AdView)this.findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder()
	    	.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	    	.build();
	    adView.loadAd(adRequest);
		
		//inicializar os componentes utilizados no layout 
		input = (EditText)findViewById(R.id.inputNumbers);
		result = (TextView)findViewById(R.id.result);
		error1 = Toast.makeText(getApplicationContext(), "Input not accepted\ntry entering a number", Toast.LENGTH_SHORT );
		error2 = Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT );
		about = Toast.makeText(getApplicationContext(), "Made by:\nAFElectronics�\n2014", Toast.LENGTH_LONG );
		
		input.addTextChangedListener(new TextWatcher() {

		      @Override
		      public void onTextChanged(CharSequence s, int start, int before, int count) {
		    	// TODO Auto-generated method stub
					//obter a string de entrada
					String numb = input.getText().toString();
					
					if(numb.matches("")){// se nao estiver vazia executar o codigo
						error1.show();
						result.setText(getResources().getString(R.string.result));
						return;
					}
						
					if(!Character.isDigit(numb.charAt(0))){
						error1.show();
						input.setText("");
						result.setText(getResources().getString(R.string.result));
						return;
					}
					
					if(numb.endsWith(" ")){
						input.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
					}
					else{
						input.setKeyListener(DigitsKeyListener.getInstance("0123456789 ."));
					}
					
					if(numb.contains(". ")){
						input.setText(numb.replace(". ", ".0 "));
						input.setSelection(input.length());
					}
					
					//partir a string inicial numa tabela e tirar os espa�os
					String[] numbs = numb.split(" ");
					s=numbs[numbs.length-1];
					int np=0;
					for( int i=0; i<s.length(); i++ ) {
					    if( s.charAt(i)=='.' ) {
					        np++;
					    } 
					}
					if(np>1){
						error1.show();
						input.setText(numb.substring(0, numb.length()-1));
						input.setSelection(input.length());
						return;
					}
						
					float tot=0;
					//somar todos os numeros
					for(int i=0;i<numbs.length;i++){
						//o parse faz um cast de string para float 
						tot+=Float.parseFloat(numbs[i]);
					}
					//dividir pelo numero de entradas
					float mean = tot/numbs.length;
					//apresentar o resultado
					result.setText(String.valueOf(mean));
		      }

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		    });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_about) {
			//abrir uma segunda actividade que vai ter as informa��es do about
			about.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
