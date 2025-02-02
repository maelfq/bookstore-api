import { useEffect, useState } from "react";
import { BookDto, PhysicalBookDto } from "./service/requests";
import { useSearchParams, useLocation } from 'react-router-dom';

export function BookPage() {

    const location = useLocation();
    const bookDto: BookDto = location.state["bookDto"];
    console.log(bookDto);

    const [book2Dto, setBookDto] = useState<BookDto | null>(null);
    const [physicalBookDtos, setPhysicalBookDtos] = useState<PhysicalBookDto[]>([]);

    useEffect( () => {
        console.log('bookPage');
        // retrieve physical books for given bookId
    })

    function rentPhysicalBook() {
        return;
    }

    return (
        <div>
            <h2>{bookDto.title}</h2>
        </div>
    );
}