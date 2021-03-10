

package com.example.android.exemplofragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass that shows a question
 * with radio buttons for providing feedback. If the user
 * clicks "Yes" the text header changes to "Article: Like".
 * If the user clicks "No" the text header changes to "Thanks".
 */
public class ExemploFragment extends Fragment {

    // valores do radio buttom,
    // 2 = default (não definido).
    private static final int YES = 0;
    private static final int NO = 1;

    public ExemploFragment() {
        // construtor vazio obrigatórios
    }

    public static ExemploFragment newInstance() {
        return new ExemploFragment();
    }

    /**
     * Cria a view para o fragment
     *
     * @param inflater           LayoutInflater inicia as views para o fragment
     * @param container          ViewGroup de uma view mae para vincular o fragment
     * @param savedInstanceState Bundle para estados anteriores
     * @return rootView
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inicia o layoute para o fragmento
        final View rootView = inflater.inflate(R.layout.fragment_exemplo,
                container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);

        //  define o onCheckedChanged listener.
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        View radioButton = radioGroup.findViewById(checkedId);
                        int index = radioGroup.indexOfChild(radioButton);

                        TextView textView = rootView.findViewById(R.id.fragment_header);

                        switch (index) {
                            case YES:
                                textView.setText(R.string.yes_message);
                                break;
                            case NO:
                                textView.setText(R.string.no_message);
                                break;
                            default:
                                break;
                        }
                    }
                });

        // retorna a view para a UI
        return rootView;
    }
}
