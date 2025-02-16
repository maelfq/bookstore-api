import { useEffect, useState } from "react";
import { CurrentUser, getBooksByUser, HttpRequestError, isHttpRequestError, PhysicalBookDto, updatePhysicalBook } from "./service/requests";
import checkIcon from './assets/material_check_icon.png'

export function MyBooksPage(): JSX.Element {
    if(CurrentUser.email == undefined) {
        return (
            <div>
                You are logged out. You cannot access this page.
            </div>
        );

    }

    const [books, setBooks] = useState<PhysicalBookDto[]>([]);
    const email: string = CurrentUser?.email;

    useEffect(() => {
        getBooksByUser(email)
        .then((data: PhysicalBookDto[] | HttpRequestError) => {
            if(isHttpRequestError(data)) {
                const error: HttpRequestError = data as HttpRequestError;
                window.alert(`Error!\n${error.httpErrorStatus}: ${error.message}`);
            }
            else {
                const books: PhysicalBookDto[] =  data as PhysicalBookDto[];
                setBooks(books);
            }
        });
    }, [])

    return (
        <div>
            <h2>Here are all the books you borrowed</h2>
            <DisplayMyBooks books={books} />
        </div>
    );
}

interface MyBooksProp {
    books: PhysicalBookDto[]
}

export function DisplayMyBooks(booksProp: MyBooksProp): JSX.Element {
    const books: PhysicalBookDto[] = booksProp.books;

    const displayedBooks: JSX.Element[] = books.map((book) => {
        return(
            <MyPhysicalBookRow key={book.physicalBookId} book={book} />
        )
    }) 
    return (
        <div>
            {displayedBooks}
        </div>
    );
}

interface MyPhysicalBookRowProp {
    book: PhysicalBookDto
}

export function MyPhysicalBookRow(bookProp: MyPhysicalBookRowProp): JSX.Element {
    const book: PhysicalBookDto = bookProp.book;

    function freePhysicalBook() {
        
        book.customerDto = undefined;

        updatePhysicalBook(book)
        .then((data: PhysicalBookDto | HttpRequestError) => {
            if(isHttpRequestError(data)) {
                const error: HttpRequestError = (data as HttpRequestError);
                window.alert(`Error:\n${error.httpErrorStatus}: ${error.message}`)
            }
            else {
                const physicalBook: PhysicalBookDto = data as PhysicalBookDto;
                window.alert(`You freed the physical book ${physicalBook.physicalBookId}`);
            }
        });
    }

    return (
        <div key={book.physicalBookId} className="book-list-entry">
            <div className="book-list-entry-text">
                {book.bookDto.title} - <b>{book.bookDto.author}</b></div>
            <div>
                <button className="row-button" aria-placeholder="Free book" title="Free book" onClick={freePhysicalBook}>
                    <img src={checkIcon} className="material-icon" alt="Free book" />
                </button>
            </div>
        </div>
    );
}