package com.hillel.artemjev.notebook.servlets;

import com.hillel.artemjev.notebook.repository.UberFactory;
import com.hillel.artemjev.notebook.repository.note.NotesRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class NoteDeleteServlet extends HttpServlet {

    private NotesRepository notesRepository = UberFactory
            .instance()
            .getNotesRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String strId = req.getParameter("id");
        notesRepository.remove(Integer.valueOf(strId));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
