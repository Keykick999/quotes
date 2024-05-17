document.addEventListener('DOMContentLoaded', function() {
    fetch('/api/quotes') // Corrected API endpoint
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network error');
        })
        .then(data => {
            const quotesDiv = document.getElementById('quotes');
            data.forEach(item => {
                const quoteElement = document.createElement('p');
                quoteElement.textContent = `${item.quoteText}, (${item.quoteSpeaker})`; // Corrected property names and element
                quoteElement.style.marginBottom = '20px';
                quotesDiv.appendChild(quoteElement); // Append to quotesDiv instead of quotes
            });
        })
        .catch(error => {
            console.error('Failed to fetch data:', error);
        });
});
