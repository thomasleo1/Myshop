package DAO;

import Model.Collocazione;

import java.util.ArrayList;

public interface ICollocazioneDAO {

    ArrayList<Collocazione> findAllByIdMagazzino(int idMagazzino);
}
