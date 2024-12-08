create TABLE IF NOT EXISTS tasks (

    id INT NOT NULL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    created_date DATE NOT NULL,
    due_date DATE NOT NULL,
    status TINYINT NOT NULL DEFAULT 0
);




