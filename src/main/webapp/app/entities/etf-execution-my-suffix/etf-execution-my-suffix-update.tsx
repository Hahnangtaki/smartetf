import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './etf-execution-my-suffix.reducer';
import { IEtfExecutionMySuffix } from 'app/shared/model/etf-execution-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEtfExecutionMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEtfExecutionMySuffixUpdateState {
  isNew: boolean;
}

export class EtfExecutionMySuffixUpdate extends React.Component<IEtfExecutionMySuffixUpdateProps, IEtfExecutionMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { etfExecutionEntity } = this.props;
      const entity = {
        ...etfExecutionEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/etf-execution-my-suffix');
  };

  render() {
    const { etfExecutionEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.etfExecution.home.createOrEditLabel">Create or edit a EtfExecution</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : etfExecutionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="etf-execution-my-suffix-id">ID</Label>
                    <AvInput id="etf-execution-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="basketOrderIdLabel" for="etf-execution-my-suffix-basketOrderId">
                    Basket Order Id
                  </Label>
                  <AvField
                    id="etf-execution-my-suffix-basketOrderId"
                    type="string"
                    className="form-control"
                    name="basketOrderId"
                    validate={{
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="stockCodeLabel" for="etf-execution-my-suffix-stockCode">
                    Stock Code
                  </Label>
                  <AvField
                    id="etf-execution-my-suffix-stockCode"
                    type="text"
                    name="stockCode"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="qtyLabel" for="etf-execution-my-suffix-qty">
                    Qty
                  </Label>
                  <AvField id="etf-execution-my-suffix-qty" type="string" className="form-control" name="qty" />
                </AvGroup>
                <AvGroup>
                  <Label id="qtyOddLabel" for="etf-execution-my-suffix-qtyOdd">
                    Qty Odd
                  </Label>
                  <AvField id="etf-execution-my-suffix-qtyOdd" type="string" className="form-control" name="qtyOdd" />
                </AvGroup>
                <AvGroup>
                  <Label id="priceLabel" for="etf-execution-my-suffix-price">
                    Price
                  </Label>
                  <AvField id="etf-execution-my-suffix-price" type="string" className="form-control" name="price" />
                </AvGroup>
                <AvGroup>
                  <Label id="amountLabel" for="etf-execution-my-suffix-amount">
                    Amount
                  </Label>
                  <AvField id="etf-execution-my-suffix-amount" type="string" className="form-control" name="amount" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/etf-execution-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  etfExecutionEntity: storeState.etfExecution.entity,
  loading: storeState.etfExecution.loading,
  updating: storeState.etfExecution.updating,
  updateSuccess: storeState.etfExecution.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfExecutionMySuffixUpdate);
