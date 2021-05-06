package alura.com.br.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import alura.com.br.agenda.database.dao.TelefoneDAO;
import alura.com.br.agenda.model.Aluno;
import alura.com.br.agenda.model.Telefone;

public class BuscaTodosTelefonesDosAlunosTask extends AsyncTask<Void, Void, List<Telefone>> {

    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final TelefonesDoAlunoEncontradoListener listener;

    public BuscaTodosTelefonesDosAlunosTask(TelefoneDAO telefoneDAO, Aluno aluno,
                                            TelefonesDoAlunoEncontradoListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO
                .buscaTodosTelefonesDoAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoEncontrados(telefones);
    }

    public interface TelefonesDoAlunoEncontradoListener {
            void quandoEncontrados(List<Telefone> telefones);
    }
}


