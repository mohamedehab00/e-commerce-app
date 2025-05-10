import { Product } from "./product";

export class ProductCategory {
    constructor(
        public id: number,
        public name: string,
        public products: Product[]
    ){}
}
