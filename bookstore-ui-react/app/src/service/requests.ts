const backEndUrl: string = "http://localhost:8080/bookstore";

export class CurrentUser {
    public static email: string | undefined = undefined;
    public static name: string | undefined = undefined;
    constructor() {}
    
    static isUserConnected(): boolean {
        return CurrentUser.email != undefined;
    }
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

export async function getBooksByUser(customerEmail: string): Promise<PhysicalBookDto[] | HttpRequestError> {
    const physicalBooks: PhysicalBookDto[] = await fetch(`${backEndUrl}/api/bookstore/customer/books?customerEmail=${customerEmail}`)
    .then((response: Response) => response.json())
    .catch(error => console.warn(error));
    return physicalBooks;
}

export async function rentBookByBookId(bookDto: BookDto, customerEmail: string): Promise<PhysicalBookDto | HttpRequestError> {
    const physicalBookDto: PhysicalBookDto = await fetch(`${backEndUrl}/api/bookstore/book/rent-book?username=${customerEmail}`,
        {
            headers: {
                "Content-Type": "application/json"
            },
            method: "POST",
            body: JSON.stringify(bookDto)
        }
    )
    .then((response: Response) => response.json())
    .catch(error => console.warn(error));
    return physicalBookDto;
}

export async function updatePhysicalBook(physicalBook: PhysicalBookDto): Promise<PhysicalBookDto | HttpRequestError> {
    const physicalBookDto: PhysicalBookDto = await fetch(`${backEndUrl}/api/bookstore/book/physical/rent`,
        {
            headers: {
                "Content-Type": "application/json"
            },
            method: "PUT",
            body: JSON.stringify(physicalBook)
        }
    )
    .then((response: Response) => response.json())
    .catch(error => console.warn(error));
    return physicalBookDto;
} 

export async function signUp(customerDto: CustomerDto): Promise<CustomerDto | HttpRequestError> {
    const signUpResponse: CustomerDto | HttpRequestError = await fetch(`${backEndUrl}/api/bookstore/customer/sign-up`,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(customerDto)
        }
    )
    .then((response: Response) => response.json())
    .catch(error => console.warn(error));
    return signUpResponse;
}

export async function login(customerDto: CustomerDto): Promise<ResponseLoginDto | HttpRequestError> {
    const loginResponse: ResponseLoginDto | HttpRequestError = await fetch(`${backEndUrl}/api/bookstore/customer/login`,
        {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(customerDto)
        }
    )
    .then((response: Response) => response.json())
    .catch(error => console.warn(error));
    return loginResponse;
}

export function isHttpRequestError(data: any) {
    return (<HttpRequestError>data).httpErrorStatus !== undefined;
}

export interface HttpRequestError {
    httpErrorStatus: string,
    message: string
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
    name?: string,
    password: string
}

export interface ResponseLoginDto {
    email: string,
    name: string
}