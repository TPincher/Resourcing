# Resourcing

{add test badges here, all projects you build from here on out will have tests, therefore you should have github workflow badges at the top of your repositories: Github Workflow Badges}
Demo & Snippets

    Include hosted link
    Include images of app if CLI or Client App

Requirements / Purpose

    This is a two-part project for post-course study. Using the [Open Trivia Database](https://opentdb.com/), the goal is to create a front-end that allows players to interact with a series of questions pulled from this database. The second part of the project is to create a backend and database which will keep track of game details, so that a user can see their record over time and re-attempt failed questions.

    Part 1 MVP
    Create an interface that will allow a user to choose level of difficulty and start a new game
    When game starts, the user should see a question card with 4 possible answers
    If they answer the question correct, a new question should appear on the screen
    If their answer is incorrect, the game is over
    Display the score (number of question answered correctly) at the end of each game
    Add a "Play Again" button under the score


    Part 2 MVP
    When the user completes a quiz it gets submitted to the API that keeps track of all game details:
        score
        date played
        questions answered
        submitted answer for each question
        correct answer for each question
        if a question was failed or not
    One of the API endpoints should allow filtering questions by failed
    On the frontend, the user should be able to view questions that they answered wrong
    They should be able to attempt those questions again
    If they answer the question correct, it should get archived in the database


    purpose of project

    This project will be built with a MySQL database, Spring Boot and Java for the backend, and with a React TypeScript frontend. Redux will also be used for front end state management.

Build Steps

    how to build / run project
    use proper code snippets if there are any commands to run

Design Goals / Approach

    Design goals
    why did you implement this the way you did?

Features

    What features does the project have?
    list them...

Known issues

    Remaining bugs, things that have been left unfixed
    Features that are buggy / flimsy

Future Goals

    What are the immediate features you'd add given more time

Change logs

6/5/2024

- Initial project commit
- Created SpringBoot project, unpacked and created the 'jobs' and 'temps' classes

7/5/2024

- Filled out controllers, services and repositories for 'jobs' and 'temps'
- Implemented ability to filter jobs which are assigned or not on the backend by passing in an optional 'assigned' parameter.
- 'Assigned' parameter can also be used to revert assinged temp to 'null' by passing in a value of -1
- Jobs can also be created with no assigned temp by passing a value of -1. This should be the default on the front-end.
- Adjusted classes to have 'job' object return for 'temp' and vice versa.

8/5/2024

- Finished up the last of the backend, so that a user can check which temps are available for a given job. This works through a custom SQL query in the repository, which was a first for me. Hit trouble with returning a full array of nulls, but managed my way around it using coalesce to ensure the subquery is never just an array of null values. It now works. It took hours.
- Reworked the job filter to remove logic from the controller layer and implement it in the service layer.

9/5/2024

- Fixed the SQL query in the job repository - added a second query instead of adding a variable for code readability.
- Removed logic from job controller, transferred it to the job service instead.
- Added in date validation to ensure that endDate is always at the same time or after the startDate for both creation and updating jobs. Decided to do this in the service layer.
- Fixed null check issue in job filter
- Started work on the front end, as it will be hard to test auth functions without it. Will use Material UI as it seems like a popular library, performs well in terms of accessibility and covers my biggest weakness - design.

What did you struggle with?

    What? Why? How?

Licensing Details

    What type of license are you releasing this under?

Further details, related projects, reimplementations

    Is this project a reimplementation for something you've done in the past? if so explain it and link it here.
    If it's an API, is there a client app that works with this project? link it
