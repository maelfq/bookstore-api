import { useState, useEffect } from "react";
import { BookDto, getAllBooks } from "./service/requests";
import { renderDisplayedBooks } from "./BookstoreHomepage";

export function AllBooksPage(): JSX.Element {
    const [books, setBooks] = useState<BookDto[]>([]);
    const [displayedBoooks, setDisplayedBooks] = useState<JSX.Element[]>([]);
    const [filterOfBooks, setFilterOfBooks] = useState<string>('');

    useEffect(() => {
        getAllBooks()
        .then( (data) => {
            setBooks(data);
            setDisplayedBooks(renderDisplayedBooks(data))
            console.log(data);
        });
    },[]);

    function filterBooks(): void {
        const filterString: string = filterOfBooks;
        let filteredBooks: BookDto[] = [];
        if(filterString == "" || filterString == undefined) {
            filteredBooks = books;
        }
        else {
            for (let i = 0; i < books.length; i++) {
                const bookSearch: string = books[i].author + books[i].title + books[i].genre + books[i].bookId;
                if (bookSearch.includes(filterString)) {
                    filteredBooks.push(books[i]);
                }
            }
        }
        setDisplayedBooks(renderDisplayedBooks(filteredBooks))
    }

    return (
        <div>
            <h1>All books</h1>
            <div className="search-container">
                <div className="input-container">
                        <input type="text" className="input-text" placeholder="Filter" onChange={(e) => setFilterOfBooks(e.target.value)} /> 
                </div>
                <button onClick={filterBooks}>Apply filter</button>

            </div>


            <div>
                {displayedBoooks}
            </div>
        </div>
    );
}