const backEndUrl: string = "http://localhost:8080/bookstore";

class CurrentUser {
    public user: string | undefined = undefined;
    constructor() {}
}

export async function getAllBooks(): Promise<BookDto[]> {
    const books: BookDto[] = await fetch(`${backEndUrl}/api/get-all-books`)
    .then((response: Response) => response.json())
    .catch(error => console.warn(error));
    console.log(books); 
    return books;
}

export async function getFeaturedBooks(): Promise<BookDto[]> {
    const books: BookDto[] = await fetch(`${backEndUrl}/api/featured-books`)
    .then((response: Response) => response.json())
    .catch(error => console.warn(error));
    console.log(books); 
    return books;
}

export async function getPhysicalBooksById(id: number): Promise<PhysicalBookDto[]> {
    const physicalBooks: PhysicalBookDto[] = await fetch(`${backEndUrl}/api/get-books-by-id?bookId=${id}`)
    .then((response: Response) => response.json())
    .catch(error => console.warn(error));
    return physicalBooks;
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
    userId: number,
    name: string
}
