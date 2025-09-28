-- Fixed N-Library Database Schema to match Java model
USE library_manage;

-- Create Users table (matching Java User model)
CREATE TABLE IF NOT EXISTS users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    userNic VARCHAR(20),
    image VARCHAR(255),
    email VARCHAR(100) UNIQUE NOT NULL,
    phoneNumber VARCHAR(20),
    address TEXT,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    userType ENUM('ADMIN', 'STUDENT') NOT NULL DEFAULT 'STUDENT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create Book Categories table
CREATE TABLE IF NOT EXISTS book_categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Books table
CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id VARCHAR(20) UNIQUE NOT NULL,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    category_id INT,
    description TEXT,
    image_path VARCHAR(255),
    available_copies INT DEFAULT 1,
    total_copies INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES book_categories(id) ON DELETE SET NULL
);

-- Create Borrowed Books table
CREATE TABLE IF NOT EXISTS borrowed_books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    book_id INT NOT NULL,
    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE NULL,
    status ENUM('BORROWED', 'RETURNED', 'OVERDUE') DEFAULT 'BORROWED',
    fine_amount DECIMAL(10,2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES users(userId) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_books_book_id ON books(book_id);
CREATE INDEX idx_books_category ON books(category_id);
CREATE INDEX idx_borrowed_user ON borrowed_books(userId);
CREATE INDEX idx_borrowed_book ON borrowed_books(book_id);
CREATE INDEX idx_borrowed_status ON borrowed_books(status);