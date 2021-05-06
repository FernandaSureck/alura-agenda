package alura.com.br.agenda.ui;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import alura.com.br.agenda.asynctask.BuscaAlunoTask;
import alura.com.br.agenda.asynctask.RemoveAlunoTask;
import alura.com.br.agenda.database.AgendaDatabase;
import alura.com.br.agenda.database.dao.AlunoDAO;
import alura.com.br.agenda.model.Aluno;
import alura.com.br.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        dao = AgendaDatabase.getInstance(context).getAlunoDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("ATENÇÃO")
                .setMessage("Tem certeza que deseja exluir este aluno?")
                .setPositiveButton("SIM", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    remove(alunoEscolhido);
                })
                .setNegativeButton("NÃO", null)
                .show();
    }

    public void atualizaAlunos() {
        new BuscaAlunoTask(dao, adapter).execute();
    }

    private void remove(Aluno aluno) {
        new RemoveAlunoTask(dao, adapter, aluno).execute();
    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }
}
