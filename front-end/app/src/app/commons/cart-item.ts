export class CartItem {
    constructor(
        public id: number,
        public name: string,
        public price: number,
        public imageUrl: string,
        public quantity: number,
    ){}
}
