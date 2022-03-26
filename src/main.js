const arr = [
    [1, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 1, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 1],
    [0, 0, 0, 0, 0, 1, 0, 0],
    [0, 0, 1, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 1, 0],
    [0, 1, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 1, 0, 0, 0, 0],
];

function hasConflict(board) {
    // check row
    for (let i = 0; i < board.length; ++i) {
        if (board[i].filter(n => n === 1).length !== 1) {
            return true;
        }
    }

    // check col
    for (let i = 0; i < board.length; ++i) {
        const container = [];
        for (let j = 0; j < board.length; ++j) {
            if (board[j][i] === 1) {
                container.push(1);
            }
        }
        if (container.length !== 1) {
            return true;
        }
    }

    // check diagonal
    for (let i = 0; i < board.length; ++i) {
        const queenIdx = board[i].indexOf(1);

        let row = i - 1;
        // check up left direction
        for (let j = queenIdx - 1; j >= 0 && row >= 0; --j) {
            if (board[row][j] === 1) {
                return true;
            }
            --row;
        }
        // check down left direction
        row = i + 1;
        for (let j = queenIdx - 1; j >= 0 && row < board.length; --j) {
            if (board[row][j] === 1) {
                return true;
            }
            ++row;
        }
        // check up right direction
        row = i - 1;
        for (let j = queenIdx + 1; j < board.length && row >= 0; ++j) {
            if (board[row][j] === 1) {
                return true;
            }
            --row;
        }
        // check down right direction
        row = i + 1;
        for (let j = queenIdx + 1; j < board.length && row < board.length; ++j) {
            if (board[row][j] === 1) {
                return true;
            }
            ++row;
        }
    }
    return false;
}

console.log(hasConflict(arr));
