-- Sample data for N-Library
USE library_manage;

-- Insert sample admin user (password: admin123)
INSERT INTO users (username, email, password, first_name, last_name, user_type) VALUES
('admin', 'admin@nlibrary.com', 'admin123', 'Admin', 'User', 'ADMIN');

-- Insert sample book categories
INSERT INTO book_categories (category_name, description) VALUES
('Programming', 'Programming and software development books'),
('Fiction', 'Fiction and literature books'),
('Science', 'Science and research books'),
('History', 'History and historical books'),
('Technology', 'Technology and engineering books');

-- Insert sample books
INSERT INTO books (book_id, title, author, isbn, category_id, description, available_copies, total_copies) VALUES
('BK001', 'Java Programming', 'John Smith', '978-0123456789', 1, 'Complete guide to Java programming', 5, 5),
('BK002', 'Python for Beginners', 'Jane Doe', '978-0123456790', 1, 'Learn Python from scratch', 3, 3),
('BK003', 'The Great Gatsby', 'F. Scott Fitzgerald', '978-0123456791', 2, 'Classic American novel', 2, 2),
('BK004', 'Introduction to Physics', 'Albert Einstein', '978-0123456792', 3, 'Fundamentals of physics', 4, 4),
('BK005', 'World War II History', 'Winston Churchill', '978-0123456793', 4, 'Complete history of WWII', 3, 3);