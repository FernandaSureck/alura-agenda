package alura.com.br.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import alura.com.br.agenda.database.converter.ConversorCalendar;
import alura.com.br.agenda.database.converter.ConversorTipoTelefone;
import alura.com.br.agenda.database.dao.AlunoDAO;
import alura.com.br.agenda.database.dao.TelefoneDAO;
import alura.com.br.agenda.model.Aluno;
import alura.com.br.agenda.model.Telefone;

import static alura.com.br.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String NOME_BANCO_DE_DADOS = "agenda.db";
    public abstract AlunoDAO getAlunoDAO();
    public abstract TelefoneDAO getTelefoneDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS)
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }
}
