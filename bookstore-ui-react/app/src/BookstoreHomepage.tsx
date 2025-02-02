import { useEffect, useState } from "react";
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import { BookDto, getFeaturedBooks } from "./service/requests";
import './bookstore-theme.css';
import NotFoundPage from "./NotFoundPage";
import { BookPage } from "./BookPage";

function BookstoreHomepage() {
    const [books, setBooks] = useState<BookDto[]>([]);
    const [displayedFeaturedBooks, setDisplayedFeaturedBooks] = useState<JSX.Element[]>([]);

    function renderDisplayedFeaturedBooks(data: BookDto[]) {
        const books = data.map((book) => {
            return (
                <div key={book.bookId}>
                    "{book.title}" par <b>{book.author}</b> <button onClick={() => displayBook(book)}>Consulter</button>
                    <Link to="/book" state={{bookDto: book}}>Contact</Link>
                </div>
            );
    });
        return books;
    }

    function displayBook(book: BookDto) {
        console.log(book)
    }

    function rentRandomBook(): BookDto | null {
        alert('Fonctionnalité non implémentée pour le moment');
        return null;
    }

    useEffect(() => {
        getFeaturedBooks()
        .then( (data) => {
            setBooks(data);
            setDisplayedFeaturedBooks(renderDisplayedFeaturedBooks(data))
        })
        .finally(() => console.log('yo'));

    }, [])
    return (
        <div className="bookstore-homepage">
            <h1>Bienvenue à la bibliothèque</h1>
            <button onClick={rentRandomBook}>Emprunter un livre au hasard !</button>
            <br />
            { books != undefined && (
                <p>Voici une sélection de {books.length} livres que vous pourriez aimer :</p>   
            )}
            <ul>{displayedFeaturedBooks}</ul>

        </div>
    )
}

export function BookstoreNavbar() {
     return (
        <div className="bookstore-navbar">
            <button> Accueil </button>
        </div>
    );
}

export function BookstoreIndex() {
    return (
        <div>
            <BookstoreNavbar />
            <BrowserRouter>
                <Routes>
                    <Route index path="home" element={<BookstoreHomepage/>} />
                    <Route path="*" element={<NotFoundPage/>} />
                    <Route path="book" element={<BookPage />} />
                </Routes>
            </BrowserRouter>
        </div>

    )
};