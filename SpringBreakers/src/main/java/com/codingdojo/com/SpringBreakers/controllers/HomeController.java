package com.codingdojo.com.SpringBreakers.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.com.SpringBreakers.models.LoginUser;
import com.codingdojo.com.SpringBreakers.models.Ticket;
import com.codingdojo.com.SpringBreakers.models.User;
import com.codingdojo.com.SpringBreakers.services.TicketServ;
import com.codingdojo.com.SpringBreakers.services.UserServ;

@Controller
public class HomeController {

	@Autowired
	private UserServ userServ;
	
	@Autowired
	private TicketServ ticketServ;
	
	// ==========================
    //      MAIN FUNCTIONS
    // ==========================
	@GetMapping("/")
	public String index(HttpSession session, Model model) {
		if(session.getAttribute("user_id") != null) {
			return "redirect:/home";
		} else {
			model.addAttribute("newUser", new User());
	        model.addAttribute("newLogin", new LoginUser());
	        	return "logReg.jsp";
		}
	}
	
	@GetMapping("/home")
	public String home (HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		} else {
			model.addAttribute("theUser", userServ.getUser((Long)session.getAttribute("user_id")));
	        return "index.jsp";
		}
	}
	
    // ==========================
    //      LOGREG FUNCTIONS
    // ==========================
	
   // Create User Process
   @PostMapping("/register")
   public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
   	userServ.register(newUser, result);
       if(result.hasErrors()) {
           model.addAttribute("newLogin", new LoginUser());
           return "logReg.jsp";
       }
       session.setAttribute("user_id", newUser.getId());
       return "redirect:/home";
   }
   
   // Login User Process
   @PostMapping("/login")
   public String loginUser(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
       User user = userServ.login(newLogin, result);
       if(result.hasErrors()) {
           model.addAttribute("newUser", new User());
           return "logReg.jsp";
       }
       session.setAttribute("user_id", user.getId());
       return "redirect:/home";
   }
   // Logout User
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
		
		  // ==========================
	    //      VOLUNTEER FUNCTIONS
	    // ==========================
		
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, @ModelAttribute("ticket") Ticket ticket, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		} else {
			model.addAttribute("theUser", userServ.getUser((Long)session.getAttribute("user_id")));
			model.addAttribute("allTickets",ticketServ.getAll());
	        return "dashboard.jsp";
		}
	}
	
	
		@GetMapping("/ticket/new")
		public String addVolunteer(HttpSession session, @ModelAttribute("saveTicketForm") Ticket ticket, Model model) {
			if(session.getAttribute("user_id") == null) {
				return "redirect:/logReg";
			} else {
				return "addTicket.jsp";
			}
		}
		
		@PostMapping("/saveTicket")
		public String saveTicket(@Valid @ModelAttribute("saveTicketForm") Ticket ticket, BindingResult result, Model model) {
			if(result.hasErrors()) {
				return "addTicket.jsp";
			} else {
				ticketServ.saveOne(ticket);
				return "redirect:/dashboard";
			}
		}
		
		@GetMapping("tickets/{id}")
		public String viewTicket (HttpSession session, @PathVariable("id") Long id, @ModelAttribute("ticket") Ticket ticket, Model model) {
			if(session.getAttribute("user_id") == null ) {
				return "redirect:/logReg";
			} else {
				model.addAttribute("theTicket", ticketServ.getOne(id));
				return "viewTicket.jsp";
			}
		}
		
		@GetMapping("tickets/{id}/edit")
		public String editTicket(HttpSession session, @PathVariable("id")Long id, @ModelAttribute("editTicketForm") Ticket ticket, Model model) {
			if(session.getAttribute("user_id") == null) {
				return "redirect:/logReg";
			} else {
				model.addAttribute("theTicket", ticketServ.getOne(id));
				return "editTicket.jsp";
			}
		}
		
		@PutMapping("tickets/{id}/update")
		public String updateVolunteer(@PathVariable("id") Long id, @Valid @ModelAttribute("editTicketForm") Ticket editTicket, BindingResult result, Model model) {
			if(result.hasErrors()) {
				model.addAttribute("theTicket", ticketServ.getOne(id));
				return "editTicket.jsp";
			} else {
				ticketServ.update(editTicket);
				return "redirect:/tickets/{id}";
			}
		}
		
		@GetMapping("ticket/{id}/delete")
		public String deleteTicket(@PathVariable("id") Long id) {
			ticketServ.deleteTicket(id);
			return "redirect:/dashboard";
		}
		@PutMapping("/tickets/{id}/bookmark")
		public String bookmark(@PathVariable("id")Long ticketId, HttpSession session) {
			Long userId = (Long) session.getAttribute("user_id");
			ticketServ.likeTicket(ticketId, userId);
			
			return "redirect:/dashboard";
		}
		
		@PutMapping("/tickets/{id}/unbookmark")
		public String unBookmark(@PathVariable("id")Long ticketId, HttpSession session) {
			Long userId = (Long) session.getAttribute("user_id");
			ticketServ.unlikeTicket(ticketId, userId);
			
			return "redirect:/dashboard";
		}
}
