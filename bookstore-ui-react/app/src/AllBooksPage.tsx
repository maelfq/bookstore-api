import { useState, useEffect } from "react";
import { BookDto, getAllBooks } from "./service/requests";
import { renderDisplayedBooks } from "./BookstoreHomepage";

export function AllBooksPage(): JSX.Element {
    const [books, setBooks] = useState<BookDto[]>([]);
    const [displayedBoooks, setDisplayedBooks] = useState<JSX.Element[]>([]);

    useEffect(() => {
        getAllBooks()
        .then( (data) => {
            setBooks(data);
            setDisplayedBooks(renderDisplayedBooks(data))
            console.log(data);
        });
    },[]);

    return (
        <div>
            <h1>All books</h1>
            <div>
                {displayedBoooks}
            </div>
        </div>
    );
}