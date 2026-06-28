export class DateUtils {

  static toBrazilian(date: string): string {
    if (!date) {
      return '';
    }

    const [year, month, day] = date.split('-');

    return `${day}/${month}/${year}`;
  }

  static toIso(date: string): string {

    if (!date) {
      return '';
    }

    // Já está em ISO
    if (date.includes('-')) {
      return date;
    }

    const [day, month, year] = date.split('/');

    return `${year}-${month}-${day}`;
  }

}