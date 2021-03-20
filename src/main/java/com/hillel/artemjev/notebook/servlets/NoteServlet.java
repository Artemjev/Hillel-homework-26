package com.hillel.artemjev.notebook.servlets;

import com.hillel.artemjev.notebook.entities.Note;
import com.hillel.artemjev.notebook.repository.UberFactory;
import com.hillel.artemjev.notebook.repository.note.NotesRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class NoteServlet extends HttpServlet {

    private NotesRepository notesRepository = UberFactory
            .instance()
            .getNotesRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        String strId = req.getParameter("id"); - как вариант
        int start = req.getContextPath().length() + req.getServletPath().length();
        String strId = req.getRequestURI().substring(start + 1);
        Note note = notesRepository.getNote(Integer.valueOf(strId));
        req.setAttribute("note", note);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/views/note.jsp")
                .forward(req, resp);
    }
}

