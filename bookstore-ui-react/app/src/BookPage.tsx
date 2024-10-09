import { useEffect, useState } from "react";
import { BookDto, CurrentUser, HttpRequestError, isHttpRequestError, PhysicalBookDto, updatePhysicalBook } from "./service/requests";
import { useLocation } from 'react-router-dom';
import { getPhysicalBooksById } from "./service/requests";
import bookLogo from "./assets/material_book_icon.png";
import checkLogo from "./assets/material_check_icon.png"

export function BookWithPhysicalBooksPage() {

    const location = useLocation();
    const bookDto: BookDto = location.state["bookDto"];

    const [physicalBookDtos, setPhysicalBookDtos] = useState<PhysicalBookDto[]>([]);
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
            <div className="physical-books-table-container">
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
        <div className="physical-book-container">
            <h2>`{bookDto.title}` by {bookDto.author}</h2>
            {physicalBookDtos.length} physical books
            <br /> 
            {physicalBooksDisplayed}
        </div>
    );
}

interface PhysicalBookRowProp {
    physicalBookDto: PhysicalBookDto
}

export function PhysicalBookRow(physicalBookRowProp: PhysicalBookRowProp) {
    const physicalBook: PhysicalBookDto = physicalBookRowProp.physicalBookDto;
    let statusMessage: string;
    if(physicalBook && physicalBook.customerDto && physicalBook.customerDto.email) {
        statusMessage =  `Currently rented by ${physicalBook?.customerDto.email}`;
    }
    else {
        statusMessage = "Book available";
    }

    function RentOrFreeOrDisabledPhysicalBookButton(): JSX.Element {
        const isBookRentedToCurrentUser = (physicalBook?.customerDto != null) && (physicalBook.customerDto.email == CurrentUser.email);
        const freeBookButtonEnabled: boolean = isBookRentedToCurrentUser ? true : false;
        if(physicalBook?.customerDto != null) {
            return (
                <button disabled={freeBookButtonEnabled} >
                    <img src={checkLogo} className="material-icon" alt="Free book" onClick={freePhysicalBook}/>
                </button>
            );
        }
        return (
            <button className="row-button" aria-placeholder="Rent book" title="Rent book" onClick={rentPhysicalBook}>
                <img src={bookLogo} className="material-icon" alt="Rent book" />
            </button>
        );
    }

    function rentPhysicalBook(): void {
        // TODO
        if(CurrentUser.email == undefined) {
            window.alert("You must be authentificated to interact")
        }
        else {
            physicalBook.customerDto = {
                email: CurrentUser.email,
                password: ''
            }
            console.log(physicalBook);
            
            updatePhysicalBook(physicalBook)
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
    }

    function freePhysicalBook(): void {
        // TODO
        if(CurrentUser.email == undefined) {
            window.alert("You must be authentificated to interact")
        }
        else {
            physicalBook.customerDto = undefined;
            console.log(physicalBook);
            
            updatePhysicalBook(physicalBook)
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

    }
    
    return (
        <tr className="physical-book-entry">
            <td>{physicalBook?.physicalBookId}</td>
            <td>{physicalBook?.bookState}</td>
            <td>{statusMessage}</td>
            <td>
                <RentOrFreeOrDisabledPhysicalBookButton />
            </td>
        </tr>
    );
}
