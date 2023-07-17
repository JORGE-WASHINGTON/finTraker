package jwom.fintrak.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import jwom.fintrak.Controllers.Request.CreateTransactionRequest;
import jwom.fintrak.Controllers.Response.CreateTransactionResponse;
import jwom.fintrak.Data.TransactionRepository;
import jwom.fintrak.Model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private CreateTransactionResponse expectedResponse;
    private CreateTransactionRequest transactionRequest;

    @BeforeEach
    public void setUp() {
        transactionRequest = CreateTransactionRequest.builder()
                .type(Transaction.Type.EXPENSE)
                .date(LocalDate.ofEpochDay(20230716))
                .account_id(1L)
                .amount(152.00)
                .description("test")
                .build();
       expectedResponse = CreateTransactionResponse.builder()
                .type(Transaction.Type.EXPENSE)
                .date(LocalDate.ofEpochDay(20230716))
                .account_id(1L)
                .amount(152.00)
                .description("test")
                .id(1L)
                .build();
    }

    @Test
    public void givenValidParamsCreatesTransaction() {
        // given - precondition or setup
        given(transactionRepository.save(ArgumentMatchers.any(Transaction.class))).willReturn(Transaction.builder()
                .id(1L)
                .type(transactionRequest.getType())
                .accountId(transactionRequest.getAccount_id())
                .amount(transactionRequest.getAmount())
                .description(transactionRequest.getDescription())
                .date(transactionRequest.getDate()).build());


        // when -  action or the behaviour that we are going test
        CreateTransactionResponse actualResponse = transactionService.createTransaction(transactionRequest);

        // then - verify the output
        verify(transactionRepository, times(1)).save(ArgumentMatchers.any(Transaction.class));
        verifyNoMoreInteractions(transactionRepository);
        assertNotNull(actualResponse);
        assertEquals(actualResponse, expectedResponse);
    }
    @Test
    public void givenInvalidParamsThrows() {
        // given - precondition or setup
        given(transactionRepository.save(ArgumentMatchers.any(Transaction.class))).willReturn(Transaction.builder()
                .id(1L)
                .type(transactionRequest.getType())
                .accountId(transactionRequest.getAccount_id())
                .amount(transactionRequest.getAmount())
                .description(transactionRequest.getDescription())
                .date(transactionRequest.getDate()).build());


        // when -  action or the behaviour that we are going test
        CreateTransactionResponse actualResponse = transactionService.createTransaction(transactionRequest);

        // then - verify the output
        verify(transactionRepository, times(1)).save(ArgumentMatchers.any(Transaction.class));
        verifyNoMoreInteractions(transactionRepository);
        assertNotNull(actualResponse);
        assertEquals(actualResponse, expectedResponse);


    }
}
