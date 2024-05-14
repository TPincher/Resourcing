export const getAllTemps = async () => {
  const response = await fetch("http://localhost:8080/temps");
  if (!response.ok) {
    throw new Error("failed to load temps");
  }
  const allTemps = await response.json();
  console.log(allTemps);
  return allTemps;
};
