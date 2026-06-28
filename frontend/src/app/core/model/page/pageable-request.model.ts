
export class PageableRequest {

  constructor(
    public page: number = 0,
    public size: number = 10,
    public sort: string = 'id'
  ) { }

}