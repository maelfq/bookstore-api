import { useEffect, useState } from "react";
import { BookDto, PhysicalBookDto } from "./service/requests";
import { useSearchParams, useLocation, data } from 'react-router-dom';
import { getPhysicalBooksById } from "./service/requests";

export function BookPage() {

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


    
    function renderPhysicalBooks(books: PhysicalBookDto[]) {
        return (
            <table className="physical-books-table">
                <th></th>
            </table>
        )
    }



    return (
        <div>
            <h2>`{bookDto.title}` by {bookDto.author}</h2>
            {physicalBookDtos.length} physical books, 
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