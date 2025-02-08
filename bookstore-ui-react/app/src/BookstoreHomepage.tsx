import { useEffect, useState } from "react";
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import { BookDto, getFeaturedBooks } from "./service/requests";
import './bookstore-theme.css';
import NotFoundPage from "./NotFoundPage";
import { BookWithPhysicalBooksPage } from "./BookPage";
import bookLogo from './assets/material_book_icon.png';
import homeLogo from './assets/material_home_icon.png';

export function BookstoreIndex(): JSX.Element {
    return (
        <BrowserRouter>
            <BookstoreNavbar />
            <div className="bookstore-page-core">
                <Routes>
                    <Route index path="/" element={<BookstoreFeaturedHomepage/>} />
                    <Route path="*" element={<NotFoundPage/>} />
                    <Route path="book" element={<BookWithPhysicalBooksPage />} />
                </Routes>
            </div>
 
        </BrowserRouter>
 
    )
 };

export function BookstoreNavbar(): JSX.Element {
    return (
       <div className="bookstore-navbar">
               <Link to="/"><button className="navbar-button"><img src={homeLogo} className="material-icon" alt="Home logo" /> Home</button></Link>
       </div>
   );
}

function BookstoreFeaturedHomepage(): JSX.Element {
    const [books, setBooks] = useState<BookDto[]>([]);
    const [displayedFeaturedBooks, setDisplayedFeaturedBooks] = useState<JSX.Element[]>([]);

    function renderDisplayedFeaturedBooks(data: BookDto[]): JSX.Element[] {
        const books = data.map((book) => {
            return (
                <BookRow book={book}/>
            );
        });
        return books;
    }

    function rentRandomBook(): BookDto | null {
        alert('Feature not available at the moment!');
        return null;
    }

    useEffect(() => {
        getFeaturedBooks()
        .then( (data) => {
            setBooks(data);
            setDisplayedFeaturedBooks(renderDisplayedFeaturedBooks(data))
            console.log(data);
        });

    }, [])


    return (
        <div className="bookstore-homepage">
            <h1>Welcome to the library</h1>
            <button onClick={rentRandomBook}>Rent a random book!</button>
            <br />
            { books != undefined && (
                <p>Here are {books.length} featured books you might like:</p>   
            )}
            <div>{displayedFeaturedBooks}</div>

        </div>
    )
}

interface BookRowProp {
    book: BookDto;
}

export function BookRow(bookRowProp: BookRowProp): JSX.Element {
    const book: BookDto = bookRowProp.book;
    return (
        <div key={book.bookId} className="book-list-entry">
            <div className="book-list-entry-text">{book.title} - <b>{book.author}</b></div>
            
            <Link to="/book" state={{bookDto: book}}>
                <button><img src={bookLogo} className="material-icon" alt="Book logo" /></button>
            </Link>
        </div>
    );
}