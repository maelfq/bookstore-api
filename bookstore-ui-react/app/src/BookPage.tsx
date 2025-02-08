import { useEffect, useState } from "react";
import { BookDto, PhysicalBookDto } from "./service/requests";
import { useSearchParams, useLocation, data } from 'react-router-dom';
import { getPhysicalBooksById } from "./service/requests";

export function BookWithPhysicalBooksPage() {

    const location = useLocation();
    const bookDto: BookDto = location.state["bookDto"];

    const [physicalBookDtos, setPhysicalBookDtos] = useState<PhysicalBookDto[]>([]);
    const [availableBooksCount, setAvailableBooksCount] = useState<number>(0);
    const [physicalBooksDisplayed, setPhysicalBooksDisplayed] = useState<JSX.Element | undefined >(undefined);

    useEffect( () => {
        // retrieve physical books for given bookId
        getPhysicalBooksById(bookDto.bookId)
        .then( (data) => {
            setPhysicalBookDtos(data);
            console.log(data);
            //TODO
            setPhysicalBooksDisplayed((renderPhysicalBooks(data)));
        });
    }, [])


    //TODO: extract as component
    function renderPhysicalBooks(books: PhysicalBookDto[]): JSX.Element {
        const physicalBookRows: JSX.Element[] = books.map((book) => {
            return (
                <PhysicalBookRow key={book.physicalBookId} physicalBookDto={book}/>
            )
        });
        return (
            <div>
                <table className="physical-books-table">
                    <thead>
                        <tr>
                            <th>Physical book id</th>
                            <th>Book state</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {physicalBookRows}
                    </tbody>
                </table>
            </div>

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

interface PhysicalBookRowProp {
    physicalBookDto?: PhysicalBookDto
}

export function PhysicalBookRow(physicalBookRowProp: PhysicalBookRowProp) {
    const physicalBook: PhysicalBookDto | undefined = physicalBookRowProp?.physicalBookDto;
    let statusMessage: string;
    if(physicalBook && physicalBook.customerDto && physicalBook.customerDto.name) {
        statusMessage =  `Currently rented by ${physicalBook?.customerDto.name}`;
    }
    else {
        statusMessage = "Book available";
    }
    
    return (
        <tr>
            <td>{physicalBook?.physicalBookId}</td>
            <td>{physicalBook?.bookState}</td>
            <td>{statusMessage}</td>
            <td>
                <button>Hey</button>
            </td>
        </tr>
    );
}
