# 🏎️ Fanta Formula 1

> **Academic Project**
>
> **Fanta Formula 1** is a fantasy Formula 1 web application developed as a **mock project** for the *Software Engineering* exam at the University of Bologna. The application itself is a prototype and is **not intended for production use**. The primary goal is to demonstrate a complete software development process, covering every phase from requirements elicitation to implementation, including:
>
> - **Requirements analysis** — functional and non-functional requirements gathered from a simulated client brief
> - **Domain modelling** — UML class diagrams for both the user-facing and system domains
> - **Threat & risk analysis** — identification of potential security threats and mitigations
> - **Architectural design** — layered architecture, separation of concerns, design pattern selection
> - **Design patterns** — DAO pattern for persistence abstraction, Factory pattern for component creation, MVC via Servlet/JSP
> - **E-R data modelling** — relational database schema derived from the domain model
> - **Implementation** — working prototype built with Java EE, MySQL, and a React/HTML/CSS frontend
> - **Known issues & future developments** — documented limitations and proposed enhancements
>
> Full technical documentation is available in the [`doc/`](./doc/) folder — see the [Documentation](#-Documentation) section below.

---


Users can create and manage fantasy teams by selecting real F1 drivers, constructors, and legendary drivers, compete within private leagues, and track standings through a global leaderboard updated after each Grand Prix.

## Features

- **User registration & authentication** — standard users and league managers
- **Team creation** — pick 3 drivers, 1 constructor, and 1 legend within a budget of 100 wheels
- **League system** — create private leagues with a shareable code, or join existing ones
- **Global leaderboard** — rankings across all teams based on accumulated race points
- **Admin panel** — add Grand Prix results and manage component scores
- **Points system** — dynamic scoring based on race positions, with special abilities for legendary drivers

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | HTML, CSS, JavaScript, React (Virtual DOM) |
| Backend | Java EE, Servlet, JSP |
| Database | MySQL via JDBC |
| Server | Apache Tomcat |
| Deployment | `.war` (Web Application Archive) |
| Architecture | DAO pattern (separation of business and persistence logic) |

## Project Structure

```
/
├── src/                  # Java source files (Servlets, DAOs, model classes)
├── WebContent/           # JSP pages, HTML, CSS, JS
├── doc/                  # Documentation and presentation
│   └── FF1Presentation.pdf
└── README.md
```

## Documentation

Full project documentation and presentation slides are available in the [`doc/`](./doc/) folder:

- [FF1Presentation.pdf](./doc/FF1Presentation.pdf) — Presentation slides: domain model, E-R diagram, DAO pattern, UI screenshots, and future developments
- [FF1.pdf](./doc/FF1.pdf) — Complete technical documentation: full requirements analysis, threat analysis, architectural and design decisions, UML diagrams, and implementation notes
