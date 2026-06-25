export interface StudentRequest {
  name: string;
  birthdate: string; // dd/MM/yyyy
  genre: string; // 1 char
  phone: string;
  email: string;
  cpf: string;
  observations?: string;

  address: string;
  number: string;
  complement?: string;

  city: string;
  state: string;
  zipCode: string;
}