package com.hillel.artemjev.notebook.servlets;

import com.hillel.artemjev.notebook.entities.Note;
import com.hillel.artemjev.notebook.repository.UberFactory;
import com.hillel.artemjev.notebook.repository.note.NotesRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class NotesServlet extends HttpServlet {

    private NotesRepository notesRepository = UberFactory
            .instance()
            .getNotesRepository();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Note> notes = notesRepository.getAll();
        req.setAttribute("notes", notes);
        req.getRequestDispatcher("WEB-INF/views/notesList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!req.getParameter("name").isEmpty()) {
            notesRepository.add(
                    new Note()
                            .setName(req.getParameter("name"))
                            .setDescription(req.getParameter("description"))
            );
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
