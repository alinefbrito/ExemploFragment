/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.exemplofragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Saved instance state key.
    static final String STATE_FRAGMENT = "status do fragmento";
    private Button mButton;
    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // botão para abrir o fragment
        mButton = findViewById(R.id.open_button);

        // se mudar a configuração
        // alterar o estado do fragmento e define o texto do botão.
        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // se o fragmento é exibido muda o texto do botão para fechar.
                mButton.setText(R.string.close);
            }
        }
        //listener do botão
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });
    }

    /**
     * metodo para abrir o fragmento
    */
    public void displayFragment() {
        // Instancia o fragmento
        ExemploFragment exemploFragment = ExemploFragment.newInstance();
        // Obtem o gerenciado do fragmento e inicia a transação
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        // Adiciona o fragmento.
        fragmentTransaction.add(R.id.fragment_container,
                exemploFragment).addToBackStack(null).commit();

        // Atualiza o texto do botão.
        mButton.setText(R.string.close);
        // altera o booleano que indica se o fragmento está aberto.
        isFragmentDisplayed = true;
    }

    /**
     * método para fechar o fragment
     */
    public void closeFragment() {
        // obtem o gerenciado do fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        // verifica se o fragmento está sendo exibido
        ExemploFragment exemploFragment = (ExemploFragment) fragmentManager
                .findFragmentById(R.id.fragment_container);
        if (exemploFragment != null) {
            // Cria e confirama a operação de fechar o fragmento
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(exemploFragment).commit();
        }
        // atualiza o texto do botão
        mButton.setText(R.string.open);
        // altera o valor do booleano que indica que o fragmento está aberto/fechado.
        isFragmentDisplayed = false;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        // salva o estado do fragmento
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }
}
