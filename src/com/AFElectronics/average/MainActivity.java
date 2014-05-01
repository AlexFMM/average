package com.AFElectronics.average;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.text.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.os.Build;
import android.widget.*;

public class MainActivity extends ActionBarActivity {
	EditText  input;
	TextView result;
	Toast error1;
	Toast error2;
	Toast about;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // inicializar a actividade

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		//inicializar os componentes utilizados no layout 
		input = (EditText)findViewById(R.id.inputNumbers);
		result = (TextView)findViewById(R.id.result);
		error1 = Toast.makeText(getApplicationContext(), "Input not accepted", Toast.LENGTH_SHORT );
		error2 = Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT );
		about = Toast.makeText(getApplicationContext(), "Made by:\nAFElectronics©\n2014", Toast.LENGTH_LONG );
		

		input.addTextChangedListener(new TextWatcher() {

		      @Override
		      public void onTextChanged(CharSequence s, int start, int before, int count) {
		    	// TODO Auto-generated method stub
					//obter a string de entrada
					String numb = input.getText().toString();
					
					if(numb.matches("")){// se nao estiver vazia executar o codigo
						error1.show();
						return;
					}
					//partir a string inicial numa tabela e tirar os espaços
					String[] numbs = numb.split(" ");
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
		      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		      }

		      @Override
		      public void afterTextChanged(Editable s) {

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
			//abrir uma segunda actividade que vai ter as informações do about
			//startActivity(new Intent(this, AboutActivity.class));
			about.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
}
