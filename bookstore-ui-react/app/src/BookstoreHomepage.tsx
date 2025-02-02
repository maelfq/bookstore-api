import { useEffect, useState } from "react";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { BookDto, getFeaturedBooks } from "./service/requests";
import './bookstore-theme.css';
import NotFoundPage from "./NotFoundPage";

function BookstoreHomepage() {
    const [books, setBooks] = useState<BookDto[]>([]);
    const [displayedFeaturedBooks, setDisplayedFeaturedBooks] = useState<JSX.Element[]>([]);

    function renderDisplayedFeaturedBooks(data: BookDto[]) {
        const books = data.map((book) => {
            return ( <li key={book.bookId}>"{book.title}" par <b>{book.author}</b></li>)
    });
        return books;
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
            <div className="bookstore-navbar">
                <button> Accueil </button>
            </div>
            <h1>Bienvenue à la bibliothèque</h1>
            { books != undefined && (
                <p>{books.length}</p>   
            )}
            <button onClick={rentRandomBook}>Emprunter un livre au hasard !</button>
            <br />
            <ul>{displayedFeaturedBooks}</ul>

        </div>
    )
}

export function BookStoreIndex() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<BookstoreHomepage/>} >
                    <Route index element={<BookstoreHomepage/>}></Route>
                </Route>
                <Route path="test" element={<NotFoundPage/>}></Route>

            </Routes>
        </BrowserRouter>
    )
};