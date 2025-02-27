export type Investment = {
    id: string,
    months: number,
    value: number,
    status: "CANCELED" | "PROCESSING" | "SUCCESS"
}