import { useEffect, useState } from "react";
import { BookDto, PhysicalBookDto } from "./service/requests";
import { useSearchParams, useLocation, data } from 'react-router-dom';
import { getPhysicalBooksById } from "./service/requests";

export function BookWithPhysicalBooksPage() {

    const location = useLocation();
    const bookDto: BookDto = location.state["bookDto"];

    const [physicalBookDtos, setPhysicalBookDtos] = useState<PhysicalBookDto[]>([]);
    const [availableBooksCount, setAvailableBooksCount] = useState<number>(0);
    const [physicalBooksDisplayed, setPhysicalBooksDisplayed] = useState<JSX.Element[]>([]);

    useEffect( () => {
        // retrieve physical books for given bookId
        getPhysicalBooksById(bookDto.bookId)
        .then( (data) => {
            setPhysicalBookDtos(data);
            console.log(data);
            //TODO
            //setPhysicalBooksDisplayed(renderPhysicalBooks(data))
        });
    }, [])


    
    function renderPhysicalBooks(books: PhysicalBookDto[]): JSX.Element {
        const physicalBookRows: JSX.Element[] = books.map((book) => {
            return (
                <tr id="${book.physicalBookId}" >
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            )
        });
        return (
            <table className="physical-books-table">
                <tr>
                    <th>Physical book id</th>
                    <th>Book state</th>
                    <th>Action</th>
                </tr>
            </table>
        )
    }

    return (
        <div>
            <h2>`{bookDto.title}` by {bookDto.author}</h2>
            {physicalBookDtos.length} physical books,
            <br /> 
            {physicalBooksDisplayed}
        </div>
    );
}

export function PhysicalBookList() {

    function rentPhysicalBook() {
        return;
    }

    return (
        <div className="physical-book-list-container">

        </div>
    )
}