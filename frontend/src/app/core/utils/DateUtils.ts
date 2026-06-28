export class DateUtils {

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