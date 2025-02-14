import { useEffect, useState } from "react";
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import { BookDto, CurrentUser, getFeaturedBooks, HttpRequestError, isHttpRequestError, PhysicalBookDto, rentBook } from "./service/requests";
import './bookstore-theme.css';
import { LoginPage } from "./LoginPage";
import { NotFoundPage } from "./NotFoundPage";
import { BookWithPhysicalBooksPage } from "./BookPage";
import bookLogo from './assets/material_book_icon.png';
import homeLogo from './assets/material_home_icon.png';
import accountLogo from './assets/material_account_icon.png';
import listLogo from './assets/material_list_icon.png';
import eyeLogo from './assets/material_eye_icon.png';
import book5Logo from './assets/material_book5_icon.png';
import { SignUpPage } from "./SignUpPage";
import { AllBooksPage } from "./AllBooksPage";
import { MyAccountPage } from "./MyAccountPage";
import { MyBooksPage } from "./MyBooksPage";


export function BookstoreIndex(): JSX.Element {
    return (
        <BrowserRouter>
            <BookstoreNavbar />
            <div className="bookstore-page-core">
                <Routes>
                    <Route index path="/" element={<BookstoreFeaturedHomepage/>} />
                    <Route path="all-books" element={<AllBooksPage />} />
                    <Route path="my-books" element={<MyBooksPage />} />
                    <Route path="book" element={<BookWithPhysicalBooksPage />} />
                    <Route path="login" element={<LoginPage />} />
                    <Route path="sign-up" element={<SignUpPage />} />
                    <Route path="my-account" element={<MyAccountPage />} />
                    <Route path="*" element={<NotFoundPage/>} />
                </Routes>
            </div>
 
        </BrowserRouter>
 
    )
 };

export function BookstoreNavbar(): JSX.Element {
    return (
       <div className="bookstore-navbar">
            <div className="navbar-left">
               <Link to="/"><button className="navbar-button">
                    <img src={homeLogo} className="material-icon" alt="Home logo" /> Home</button>
                </Link>
                <Link to="/all-books"><button className="navbar-button">
                    <img src={listLogo} className="material-icon" alt="List of books logo" /> All books</button>
                </Link>
                <Link to="/my-books">
                    <button className="navbar-button">
                        <img src={book5Logo} className="material-icon"></img> My books
                    </button>
                </Link>
            </div>
            <div className="navbar-login">
                <LoginButton />
            </div>
       </div>
   );
}

export function LoginButton(): JSX.Element {
    if(CurrentUser.email != undefined) {
        return (
        <Link to="/my-account">
            <button className="navbar-button">
                <div>Account</div><img src={accountLogo} className="material-icon" alt="Account logo" />
            </button>
        </Link>);
    }
    return (
        <Link to="/login">
            <button className="navbar-button">
                <div>Login</div><img src={accountLogo} className="material-icon" alt="Account logo" />
            </button>
        </Link>
    )
}

function BookstoreFeaturedHomepage(): JSX.Element {
    const [books, setBooks] = useState<BookDto[]>([]);
    const [displayedFeaturedBooks, setDisplayedFeaturedBooks] = useState<JSX.Element[]>([]);

    function renderDisplayedFeaturedBooks(data: BookDto[]): JSX.Element[] {
        const books = data.map((book) => {
            return (
                <BookRow key={book.bookId} book={book}/>
            );
        });
        return books;
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

    function rent(): void {        
        if(CurrentUser.email != undefined) {
            rentBook(book, CurrentUser.email)
            .then((data: PhysicalBookDto | HttpRequestError) => {
                if(isHttpRequestError(data)) {
                    const error: HttpRequestError = (data as HttpRequestError);
                    window.alert(`Error:\n${error.httpErrorStatus}: ${error.message}`)
                }
                else {
                    const physicalBook: PhysicalBookDto = data as PhysicalBookDto;
                    window.alert(`You rented the physical book ${physicalBook.physicalBookId}`);
                }
                
            });
        }
        else {
            window.alert("Please log in before attempting to rent a book");
        }
    }

    return (
        <div key={book.bookId} className="book-list-entry">
            <div className="book-list-entry-text">{book.title} - <b>{book.author}</b></div>
            
            <div>
                <button className="row-button" aria-placeholder="Rent book" title="Rent book" onClick={rent}>
                    <img src={bookLogo} className="material-icon" alt="Rent book" />
                </button>
                <Link to="/book" state={{bookDto: book}} title="See book detail">
                    <button className="row-button"><img src={eyeLogo} className="material-icon" alt="See book detail"  /></button>
                </Link>
            </div>
        </div>
    );
}


export function renderDisplayedBooks(data: BookDto[]): JSX.Element[] {
    const books = data.map((book) => {
        return (
            <BookRow key={book.bookId} book={book}/>
        );
    });
    return books;
}

export function rentRandomBook(): BookDto | null {
    alert('Feature not available at the moment!');
    return null;
}
