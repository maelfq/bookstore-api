const backEndUrl: string = "http://localhost:8080/bookstore";

class CurrentUser {
    public user: string | undefined = undefined;
    constructor() {}
}

export async function getAllBooks(): Promise<BookDto[]> {
    const books: BookDto[] = await fetch(`${backEndUrl}/api/bookstore/book/get-all-books`)
    .then((response: Response) => response.json())
    .catch(error => console.warn(error));
    console.log(books); 
    return books;
}

export async function getFeaturedBooks(): Promise<BookDto[]> {
    const books: BookDto[] = await fetch(`${backEndUrl}/api/bookstore/book/featured-books`)
    .then((response: Response) => response.json())
    .catch(error => console.warn(error));
    console.log(books); 
    return books;
}

export async function getPhysicalBooksById(id: number): Promise<PhysicalBookDto[]> {
    const physicalBooks: PhysicalBookDto[] = await fetch(`${backEndUrl}/api/bookstore/book/physical/get-books-by-id?bookId=${id}`)
    .then((response: Response) => response.json())
    .catch(error => console.warn(error));
    return physicalBooks;
}

export async function rentBook(bookDto: BookDto, customerId: number): Promise<PhysicalBookDto> {
    const physicalBookDto: PhysicalBookDto = await fetch(`${backEndUrl}/api/bookstore/book/rent-book?customerId=${customerId}`,
        {
            method: "POST",
            body: JSON.stringify(bookDto)
        }
    )
    .then((response: Response) => response.json())
    .catch(error => console.warn(error));
    return physicalBookDto;
}


export interface BookDto {
    bookId: number,
    title: string,
    author: string,
    genre: string
}

export interface PhysicalBookDto {
    physicalBookId: number,
    bookState: BookStateEnum,
    bookDto: BookDto,
    customerDto: CustomerDto
}

export enum BookStateEnum {
    POOR,
    MEDIUM,
    GOOD,
    NEW
}

export interface CustomerDto {
    email: string,
    name: string,
    password: string
}
