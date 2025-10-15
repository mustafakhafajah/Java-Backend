CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS posts (
    id INTEGER PRIMARY KEY,
    content TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS comments (
    id INTEGER PRIMARY KEY,
    content TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER NOT NULL,
    post_id INTEGER NOT NULL,
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS friendships (
    id INTEGER PRIMARY KEY,
    user_one_id INTEGER NOT NULL,
    user_two_id INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_friend_user_one FOREIGN KEY (user_one_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_friend_user_two FOREIGN KEY (user_two_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT unique_friendship UNIQUE (user_one_id, user_two_id)
);