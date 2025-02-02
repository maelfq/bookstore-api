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


export interface BookDto {
    bookId: number,
    title: string,
    author: string,
    genre: string
}
